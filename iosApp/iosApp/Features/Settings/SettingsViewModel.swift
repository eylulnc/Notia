import Foundation
import Shared

@MainActor
final class SettingsViewModel: ObservableObject {

    @Published var uiState = SettingsUiState()

    private let focusRepository: FocusRepository

    init(focusRepository: FocusRepository) {
        self.focusRepository = focusRepository

        let savedRaw = UserDefaults.standard.string(forKey: "theme_mode") ?? ""
        uiState.themeMode = ThemeMode(rawValue: savedRaw) ?? .system
        uiState.appVersion = Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? ""
    }

    func onThemeSelected(_ mode: ThemeMode) {
        uiState.themeMode = mode
        UserDefaults.standard.set(mode.rawValue, forKey: "theme_mode")
    }

    func requestReset() {
        uiState.isResetDialogVisible = true
    }

    func cancelReset() {
        uiState.isResetDialogVisible = false
    }

    func confirmReset() {
        Task {
            try? await focusRepository.clearAll()
            uiState.isResetDialogVisible = false
        }
    }
}
