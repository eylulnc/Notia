import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        KoinHelper().iOSInit()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }

}
