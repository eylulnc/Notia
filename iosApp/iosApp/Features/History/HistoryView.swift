import SwiftUI
import Shared

struct HistoryView: View {

    init(viewModel: HistoryViewModel) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }

    @StateObject private var viewModel: HistoryViewModel

    var body: some View {
        VStack(spacing: 0) {
            NotiaTopBar(
                title: "history_title",
                leadingContent: { EmptyView() },
                trailingContent: { EmptyView() }
            )

            if viewModel.uiState.isLoading {
                ProgressView()
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            } else {
                VStack(spacing: 0) {
                    HistoryStreakSummaryView(
                        currentStreak: viewModel.uiState.currentStreak,
                        longestStreak: viewModel.uiState.longestStreak
                    )

                    if viewModel.uiState.isEmpty {
                        HistoryEmptyStateView()
                    } else {
                        ScrollView {
                            LazyVStack(alignment: .leading, spacing: 0) {
                                ForEach(viewModel.uiState.months) { group in
                                    HistoryMonthHeaderView(label: group.monthLabel)
                                    HistoryMonthGroupCardView(items: group.items)
                                        .padding(.bottom, NotiaSpacing.xl)
                                }
                            }
                            .padding(.horizontal, NotiaSpacing.l)
                            .padding(.bottom, NotiaSpacing.xxl)
                        }
                    }
                }
            }
        }
        .background(Color.Notia.background.ignoresSafeArea(edges: .bottom))
    }
}
