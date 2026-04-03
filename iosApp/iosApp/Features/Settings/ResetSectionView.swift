import SwiftUI

struct ResetSectionView: View {
    let onReset: () -> Void

    var body: some View {
        SettingsGroupView(title: "settings_reset") {
            Button(action: onReset) {
                Text("settings_reset_all")
                    .font(.system(size: NotiaFontSizes.body))
                    .foregroundColor(Color.Notia.accent)
            }
        }
    }
}
