import SwiftUI

struct HistoryRowView: View {
    let item: HistoryItem

    var body: some View {
        HStack(alignment: .top, spacing: NotiaSpacing.l) {
            VStack(spacing: 2) {
                Text(item.dayAbbrev)
                    .font(.system(size: NotiaFontSizes.label))
                    .foregroundColor(Color.Notia.outline)
                Text(item.dayNumber)
                    .font(.system(size: NotiaFontSizes.label, weight: .semibold))
                    .foregroundColor(Color.Notia.text)
            }
            .frame(width: 48)

            Text(item.focusText)
                .font(.system(size: NotiaFontSizes.label))
                .foregroundColor(Color.Notia.text)
                .frame(maxWidth: .infinity, alignment: .leading)
        }
        .padding(.horizontal, NotiaSpacing.l)
        .padding(.vertical, NotiaSpacing.s)
    }
}
