import Foundation

struct HistoryItem: Identifiable {
    let id = UUID()
    let dateLabel: String  // "Mon, Jan 22"
    let dayAbbrev: String  // "Mon"
    let dayNumber: String  // "22"
    let focusText: String
}

struct HistoryMonthGroup: Identifiable {
    let id = UUID()
    let monthLabel: String  // "January 2026"
    let items: [HistoryItem]
}

struct HistoryUiState {
    var months: [HistoryMonthGroup]
    var currentStreak: Int
    var longestStreak: Int
    var isLoading: Bool

    var isEmpty: Bool { months.isEmpty }

    static func loading() -> HistoryUiState {
        HistoryUiState(months: [], currentStreak: 0, longestStreak: 0, isLoading: true)
    }
}
