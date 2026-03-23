import SwiftUI

struct CreditsSectionView: View {

    var body: some View {
        SettingsGroupView(title: "settings_credits") {
            HStack(spacing: 0) {
                Text("settings_credits_prefix")
                    .foregroundColor(Color.Notia.text)
                Link(String(localized: "settings_credits_link"),
                     destination: URL(string: "https://storyset.com/people")!)
                    .foregroundColor(Color.Notia.primary)
            }
            .font(.system(size: NotiaFontSizes.body))
        }
    }
}
