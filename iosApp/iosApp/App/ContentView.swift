import SwiftUI
import Shared

struct ContentView: View {

    @State private var selectedTab: MainTab = .today
    @AppStorage("theme_mode") private var themeModeRaw: String = ThemeMode.system.rawValue
    @AppStorage("onboarding_seen") private var hasSeenOnboarding: Bool = false

    private let koinHelper = KoinHelper()

    private var themeMode: ThemeMode {
        ThemeMode(rawValue: themeModeRaw) ?? .system
    }

    var body: some View {
        Group {
            if !hasSeenOnboarding {
                OnboardingView {
                    hasSeenOnboarding = true
                }
            } else {
                mainTabView
            }
        }
        .preferredColorScheme(themeMode.colorScheme)
    }

    private var mainTabView: some View {
        TabView(selection: $selectedTab) {
            TodayView(viewModel: TodayViewModel(
                repository: koinHelper.focusRepository,
                dateProvider: koinHelper.dateProvider
            ))
            .tabItem {
                Label(MainTab.today.title, systemImage: MainTab.today.icon)
            }
            .tag(MainTab.today)

            HistoryView(viewModel: HistoryViewModel(repository: koinHelper.focusRepository))
            .tabItem {
                Label(MainTab.history.title, systemImage: MainTab.history.icon)
            }
            .tag(MainTab.history)

            SettingsView(viewModel: SettingsViewModel(focusRepository: koinHelper.focusRepository))
            .tabItem {
                Label(MainTab.settings.title, systemImage: MainTab.settings.icon)
            }
            .tag(MainTab.settings)
        }
        .tint(Color.black)
    }
}
