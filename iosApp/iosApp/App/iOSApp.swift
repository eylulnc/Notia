import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        KoinStarter().startIOS()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }

}
