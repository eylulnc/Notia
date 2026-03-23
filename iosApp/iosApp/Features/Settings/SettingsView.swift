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

                    ResetSectionView(onReset: viewModel.requestReset)

                    CreditsSectionView()

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
    }
}
