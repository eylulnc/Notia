import SwiftUI

struct SettingsGroupView<Content: View>: View {
    let title: LocalizedStringKey
    @ViewBuilder let content: () -> Content

    var body: some View {
        VStack(alignment: .leading, spacing: NotiaSpacing.s) {
            Text(title)
                .textCase(.uppercase)
                .font(.system(size: NotiaFontSizes.caption, weight: .semibold))
                .foregroundColor(Color.Notia.outline)
                .tracking(1.5)
                .padding(.horizontal, NotiaSpacing.s)

            VStack(alignment: .leading, spacing: 0) {
                content()
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(NotiaSpacing.l)
            .background(
                RoundedRectangle(cornerRadius: NotiaSpacing.l)
                    .fill(Color.Notia.surface)
            )
        }
    }
}
