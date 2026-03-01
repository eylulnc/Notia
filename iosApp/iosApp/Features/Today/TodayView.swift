import SwiftUI

struct TodayView: View {

    init(viewModel: TodayViewModel) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }

    @StateObject private var viewModel: TodayViewModel
    
    var body: some View {
        NavigationView {
            ZStack {
                if viewModel.uiState.isLoading {
                    ProgressView()
                } else if viewModel.uiState.isEditing {
                    TodayEditView(
                        initialText: viewModel.uiState.focusText,
                        onSave: { text in
                            viewModel.saveFocus(text)
                        },
                        onCancel: {
                            viewModel.cancelEditing()
                        }
                    )
                } else if viewModel.uiState.focusText == nil {
                    TodayEmptyStateView(onSetFocus: {
                        viewModel.startEditing()
                    })
                } else {
                    TodayFilledStateView(
                        focusText: viewModel.uiState.focusText ?? "",
                        onEdit: {
                            viewModel.startEditing()
                        },
                        onClear: {
                            viewModel.clearFocus()
                        }
                    )
                }
            }
            .navigationTitle("Today")
            .toolbar {
                if !viewModel.uiState.isEditing && viewModel.uiState.focusText != nil {
                    ToolbarItem(placement: .principal) {
                        StreakPill(streak: viewModel.uiState.currentStreak)
                    }
                }
            }
        }
    }
}
