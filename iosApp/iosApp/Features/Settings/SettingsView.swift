import SwiftUI
import Shared

struct SettingsView: View {

    init(viewModel: SettingsViewModel) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }

    @StateObject private var viewModel: SettingsViewModel

    var body: some View {
        VStack(spacing: 0) {
            NotiaTopBar(
                title: "settings_title",
                leadingContent: { EmptyView() },
                trailingContent: { EmptyView() }
            )

            ScrollView {
                VStack(spacing: NotiaSpacing.xl) {
                    AppearanceSectionView(
                        selected: viewModel.uiState.themeMode,
                        onSelect: viewModel.onThemeSelected
                    )

                    ReminderSectionView(
                        isEnabled: viewModel.uiState.isReminderEnabled,
                        reminderHour: viewModel.uiState.reminderHour,
                        reminderMinute: viewModel.uiState.reminderMinute,
                        onToggle: viewModel.onReminderToggled,
                        onTimeChanged: viewModel.onReminderTimeChanged
                    )

                    ResetSectionView(onReset: viewModel.requestReset)

                    AppInfoSectionView(appVersion: viewModel.uiState.appVersion)
                }
                .padding(NotiaSpacing.l)
                .padding(.bottom, NotiaSpacing.xxl)
            }
        }
        .background(Color.Notia.background.ignoresSafeArea(edges: .bottom))
        .alert(
            "settings_reset_confirm_title",
            isPresented: Binding(
                get: { viewModel.uiState.isResetDialogVisible },
                set: { if !$0 { viewModel.cancelReset() } }
            )
        ) {
            Button("settings_reset_confirm", role: .destructive) {
                viewModel.confirmReset()
            }
            Button("today_cancel", role: .cancel) {
                viewModel.cancelReset()
            }
        } message: {
            Text("settings_reset_confirm_body")
        }
        .alert(
            "settings_reminder_denied_title",
            isPresented: Binding(
                get: { viewModel.uiState.showNotificationDeniedAlert },
                set: { if !$0 { viewModel.dismissNotificationDeniedAlert() } }
            )
        ) {
            Button("settings_reminder_open_settings") {
                viewModel.openNotificationSettings()
            }
            Button("today_cancel", role: .cancel) {
                viewModel.dismissNotificationDeniedAlert()
            }
        } message: {
            Text("settings_reminder_denied_body")
        }
    }
}
