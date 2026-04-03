import Foundation
import Shared

@MainActor
final class SettingsViewModel: ObservableObject {

    @Published var uiState = SettingsUiState()

    private let focusRepository: FocusRepository
    private let notifications = NotificationService.shared

    init(focusRepository: FocusRepository) {
        self.focusRepository = focusRepository
        loadPersistedState()
    }

    // MARK: - Theme

    func onThemeSelected(_ mode: ThemeMode) {
        uiState.themeMode = mode
        UserDefaults.standard.set(mode.rawValue, forKey: "theme_mode")
    }

    // MARK: - Reminder

    func onReminderToggled(_ enabled: Bool) {
        if enabled {
            Task { await enableReminder() }
        } else {
            disableReminder()
        }
    }

    func onReminderTimeChanged(hour: Int, minute: Int) {
        uiState.reminderHour = hour
        uiState.reminderMinute = minute
        UserDefaults.standard.set(hour, forKey: "reminder_hour")
        UserDefaults.standard.set(minute, forKey: "reminder_minute")
        if uiState.isReminderEnabled {
            notifications.scheduleDaily(hour: hour, minute: minute)
        }
    }

    func dismissNotificationDeniedAlert() {
        uiState.showNotificationDeniedAlert = false
    }

    func openNotificationSettings() {
        notifications.openAppSettings()
    }

    // MARK: - Reset

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

    // MARK: - Private

    private func loadPersistedState() {
        let savedRaw = UserDefaults.standard.string(forKey: "theme_mode") ?? ""
        uiState.themeMode = ThemeMode(rawValue: savedRaw) ?? .system
        uiState.appVersion = Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? ""
        uiState.isReminderEnabled = UserDefaults.standard.bool(forKey: "reminder_enabled")
        if UserDefaults.standard.object(forKey: "reminder_hour") != nil {
            uiState.reminderHour = UserDefaults.standard.integer(forKey: "reminder_hour")
            uiState.reminderMinute = UserDefaults.standard.integer(forKey: "reminder_minute")
        }
    }

    private func enableReminder() async {
        let status = await notifications.authorizationStatus()

        switch status {
        case .authorized, .provisional:
            setReminderEnabled(true)

        case .notDetermined:
            let granted = await notifications.requestPermission()
            if granted {
                setReminderEnabled(true)
            }

        case .denied:
            uiState.showNotificationDeniedAlert = true

        default:
            break
        }
    }

    private func disableReminder() {
        uiState.isReminderEnabled = false
        UserDefaults.standard.set(false, forKey: "reminder_enabled")
        notifications.cancel()
    }

    private func setReminderEnabled(_ enabled: Bool) {
        uiState.isReminderEnabled = enabled
        UserDefaults.standard.set(enabled, forKey: "reminder_enabled")
        if enabled {
            notifications.scheduleDaily(hour: uiState.reminderHour, minute: uiState.reminderMinute)
        }
    }
}
