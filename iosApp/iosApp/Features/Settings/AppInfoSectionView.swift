import SwiftUI

struct AppInfoSectionView: View {
    let appVersion: String

    var body: some View {
        VStack(spacing: NotiaSpacing.xs) {
            Text("settings_app_name")
                .textCase(.uppercase)
                .font(.system(size: NotiaFontSizes.caption))
                .foregroundColor(Color.Notia.outline)
                .tracking(1.5)

            Text(String(format: String(localized: "settings_version"), appVersion))
                .font(.system(size: NotiaFontSizes.caption))
                .foregroundColor(Color.Notia.outline.opacity(0.7))

            Text("settings_developer")
                .font(.system(size: NotiaFontSizes.caption))
                .foregroundColor(Color.Notia.outline.opacity(0.5))
        }
        .frame(maxWidth: .infinity)
        .padding(.vertical, NotiaSpacing.xl)
    }
}
