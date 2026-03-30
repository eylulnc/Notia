import SwiftUI
import Shared

struct TodayView: View {

    init(viewModel: TodayViewModel) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }

    @StateObject private var viewModel: TodayViewModel
    @State private var showInfoSheet = false

    var body: some View {
        VStack(spacing: 0) {

            if viewModel.uiState.isEditing {
                NotiaTopBar(
                    title: "today_editing_title",
                    leadingContent: {
                        Button("today_cancel") {
                            viewModel.cancelEditing()
                        }
                        .foregroundColor(Color.Notia.outline)
                    },
                    trailingContent: { EmptyView() }
                )
            } else {
                NotiaTopBar(
                    title: "today_title",
                    leadingContent: {
                        if viewModel.uiState.currentStreak > 0 {
                            StreakPill(streak: viewModel.uiState.currentStreak)
                                .padding(.leading, NotiaSpacing.m)
                        } else {
                            EmptyView()
                        }
                    },
                    trailingContent: {
                        Button {
                            showInfoSheet = true
                        } label: {
                            Image(systemName: NotiaIcons.Today.info)
                                .foregroundColor(Color.Notia.primary)
                        }
                    }
                )
            }

            // Content
            ZStack {
                Color.Notia.background

                if viewModel.uiState.isLoading {
                    ProgressView()
                } else if viewModel.uiState.isEditing {
                    TodayEditView(
                        initialText: viewModel.uiState.focusText,
                        onSave: viewModel.saveFocus,
                        onCancel: viewModel.cancelEditing
                    )
                } else if viewModel.uiState.focusText == nil {
                    TodayEmptyStateView(
                        onSetFocus: viewModel.startEditing
                    )
                } else {
                    TodayFilledStateView(
                        focusText: viewModel.uiState.focusText ?? "",
                        onEdit: viewModel.startEditing,
                        onClear: viewModel.clearFocus
                    )
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .background(Color.Notia.background.ignoresSafeArea(edges: .bottom))
        .overlay {
            if showInfoSheet {
                ZStack {
                    Color.black.opacity(0.3)
                        .ignoresSafeArea()
                        .onTapGesture { showInfoSheet = false }

                    VStack(alignment: .leading, spacing: NotiaSpacing.l) {
                        Text(LocalizedStringKey("info_sheet_title"))
                            .font(.system(size: NotiaFontSizes.subTitle, weight: .bold))
                            .foregroundColor(Color.Notia.text)

                        Text(LocalizedStringKey("info_sheet_body"))
                            .font(.system(size: NotiaFontSizes.body))
                            .foregroundColor(Color.Notia.outline)
                            .lineSpacing(4)
                        
                        Button { showInfoSheet = false } label: {
                            Text("OK")
                                .frame(maxWidth: .infinity)
                        }
                        .buttonStyle(.bordered)
                        .tint(Color.Notia.primary)
                    }
                    .padding(NotiaSpacing.xl)
                    .background(Color.Notia.background)
                    .cornerRadius(16)
                    .shadow(color: .black.opacity(0.08), radius: 16, x: 0, y: 4)
                    .padding(.horizontal, NotiaSpacing.xl)
                }
            }
        }
    }
}

