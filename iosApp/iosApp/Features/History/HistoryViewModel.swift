import Foundation
import Shared

@MainActor
final class HistoryViewModel: ObservableObject {

    @Published private(set) var uiState = HistoryUiState.loading()

    private let repository: FocusRepository
    private let dateFormatter = HistoryDateFormatter()

    private var historyCancellable: Shared.Cancellable?
    private var currentStreakCancellable: Shared.Cancellable?
    private var longestStreakCancellable: Shared.Cancellable?

    // Raw values; rebuilt into uiState whenever any changes
    private var rawHistory: [DailyFocus] = []
    private var currentStreak: Int = 0
    private var longestStreak: Int = 0

    init(repository: FocusRepository) {
        self.repository = repository
        observe()
    }

    private func observe() {
        let helper = FlowHelper()

        historyCancellable = helper.wrap(flow: repository.getHistory()).subscribe(
            onEach: { [weak self] item in
                if let list = item as? [DailyFocus] {
                    self?.rawHistory = list
                    self?.rebuildState()
                }
            },
            onComplete: {},
            onThrow: { _ in }
        )

        currentStreakCancellable = helper.wrap(flow: repository.getCurrentStreak()).subscribe(
            onEach: { [weak self] item in
                if let value = item as? Int32 {
                    self?.currentStreak = Int(value)
                    self?.rebuildState()
                }
            },
            onComplete: {},
            onThrow: { _ in }
        )

        longestStreakCancellable = helper.wrap(flow: repository.getLongestStreak()).subscribe(
            onEach: { [weak self] item in
                if let value = item as? Int32 {
                    self?.longestStreak = Int(value)
                    self?.rebuildState()
                }
            },
            onComplete: {},
            onThrow: { _ in }
        )
    }

    private func rebuildState() {
        // Group by (year, monthNumber)
        var dict: [String: (year: Int32, monthNumber: Int32, items: [DailyFocus])] = [:]

        for focus in rawHistory {
            let key = "\(focus.date.year)-\(focus.date.monthNumber)"
            if dict[key] == nil {
                dict[key] = (focus.date.year, focus.date.monthNumber, [])
            }
            dict[key]!.items.append(focus)
        }

        let months = dict.values
            .sorted { a, b in
                if a.year != b.year { return a.year > b.year }
                return a.monthNumber > b.monthNumber
            }
            .map { group -> HistoryMonthGroup in
                let sortedItems = group.items
                    .sorted { a, b in
                        if a.date.year != b.date.year { return a.date.year > b.date.year }
                        if a.date.monthNumber != b.date.monthNumber { return a.date.monthNumber > b.date.monthNumber }
                        return a.date.dayOfMonth > b.date.dayOfMonth
                    }
                    .map { focus -> HistoryItem in
                        let label = dateFormatter.format(date: focus.date)
                        let dayAbbrev = label.components(separatedBy: ",").first ?? ""
                        let dayNumber = label.components(separatedBy: " ").last ?? ""
                        return HistoryItem(dateLabel: label, dayAbbrev: dayAbbrev, dayNumber: dayNumber, focusText: focus.text)
                    }

                return HistoryMonthGroup(
                    monthLabel: formatMonthLabel(year: group.year, monthNumber: group.monthNumber),
                    items: sortedItems
                )
            }

        uiState = HistoryUiState(
            months: months,
            currentStreak: currentStreak,
            longestStreak: longestStreak,
            isLoading: false
        )
    }

    private func formatMonthLabel(year: Int32, monthNumber: Int32) -> String {
        let names = ["January", "February", "March", "April", "May", "June",
                     "July", "August", "September", "October", "November", "December"]
        guard monthNumber >= 1, monthNumber <= 12 else { return "\(year)" }
        return "\(names[Int(monthNumber) - 1]) \(year)"
    }

    deinit {
        historyCancellable?.cancel()
        currentStreakCancellable?.cancel()
        longestStreakCancellable?.cancel()
    }
}
