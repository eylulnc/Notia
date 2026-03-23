import Foundation

struct SettingsUiState {
    var themeMode: ThemeMode = .system
    var appVersion: String = ""
    var isResetDialogVisible: Bool = false
    var isReminderEnabled: Bool = false
    var reminderHour: Int = 8
    var reminderMinute: Int = 30
    var showNotificationDeniedAlert: Bool = false
}
