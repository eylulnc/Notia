import SwiftUI

enum ThemeMode: String, CaseIterable {
    case system = "SYSTEM"
    case light = "LIGHT"
    case dark = "DARK"

    var colorScheme: ColorScheme? {
        switch self {
        case .system: return nil
        case .light:  return .light
        case .dark:   return .dark
        }
    }

    var displayName: String {
        switch self {
        case .system: return String(localized: "theme_system")
        case .light:  return String(localized: "theme_light")
        case .dark:   return String(localized: "theme_dark")
        }
    }
}
