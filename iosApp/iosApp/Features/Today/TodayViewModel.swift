//
//  TodayViewModel.swift
//  iosApp
//

import Foundation
import Shared

@MainActor
final class TodayViewModel: ObservableObject {

    @Published private(set) var uiState = TodayUiState(
        dateLabel: "",
        focusText: nil,
        currentStreak: 0,
        isEditing: false,
        isLoading: true
    )

    private let repository: FocusRepository
    private let dateProvider: DateProvider

    private var focusCancellable: Shared.Cancellable?
    private var streakCancellable: Shared.Cancellable?

    init(repository: FocusRepository, dateProvider: DateProvider) {
        self.repository = repository
        self.dateProvider = dateProvider
        observe()
    }

    private func observe() {
        let helper = FlowHelper()
        // Observe Today's Focus
        focusCancellable = helper.wrap(flow: repository.getTodayFocus()).subscribe(
            onEach: { [weak self] (item: Any?) in
                // Cast the generic 'Any' to 'DailyFocus'
                let focus = item as? DailyFocus
                self?.update(focus: focus)
            },
            onComplete: {},
            onThrow: { _ in }
        )

        // Observe Current Streak
        streakCancellable = helper.wrap(flow: repository.getCurrentStreak()).subscribe(
            onEach: { [weak self] (item: Any?) in
                // Cast the generic 'Any' to 'Int32'
                if let value = item as? Int32 {
                    self?.update(streak: Int(value))
                }
            },
            onComplete: {},
            onThrow: { _ in }
        )
    }

    private func update(focus: DailyFocus?) {
        uiState.focusText = focus?.text
        uiState.dateLabel = dateProvider.today().description
        uiState.isLoading = false
    }

    private func update(streak: Int) {
        uiState.currentStreak = streak
        uiState.isLoading = false
    }

    func startEditing() {
        uiState.isEditing = true
    }

    func cancelEditing() {
        uiState.isEditing = false
    }

    func saveFocus(_ text: String) {
        Task {
            do {
                try await repository.setTodayFocus(text: text)
                uiState.isEditing = false
            } catch {
                print("Failed to save focus: \(error)")
            }
        }
    }

    func clearFocus() {
        Task {
            do {
                try await repository.deleteTodayFocus()
            } catch {
                print("Failed to clear focus: \(error)")
            }
        }
    }

    deinit {
        focusCancellable?.cancel()
        streakCancellable?.cancel()
    }
}
