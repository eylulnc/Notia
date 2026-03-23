import SwiftUI
import Shared

struct ContentView: View {

    @State private var selectedTab: MainTab = .today

    private let koinHelper = KoinHelper()

    var body: some View {
        TabView(selection: $selectedTab) {
            TodayView(viewModel: TodayViewModel(
                repository: koinHelper.focusRepository,
                dateProvider: koinHelper.dateProvider
            ))
            .tabItem {
                Label(MainTab.today.title,
                      systemImage: MainTab.today.icon)
            }
            .tag(MainTab.today)

            HistoryView(viewModel: HistoryViewModel(repository: koinHelper.focusRepository))
            .tabItem {
                Label(MainTab.history.title,
                      systemImage: MainTab.history.icon)
            }
            .tag(MainTab.history)

            SettingsView()
            .tabItem {
                Label(MainTab.settings.title,
                      systemImage: MainTab.settings.icon)
            }
            .tag(MainTab.settings)

        }.tint(Color.black)
    }
}
