import SwiftUI
import Shared

struct TodayView: View {

    init(viewModel: TodayViewModel) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }

    @StateObject private var viewModel: TodayViewModel

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
                    leadingContent: { Image(systemName: NotiaIcons.Today.sun) },
                    trailingContent: {
                        if viewModel.uiState.focusText != nil {
                            StreakPill(streak: viewModel.uiState.currentStreak)
                        } else {
                            EmptyView()
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
    }
}

