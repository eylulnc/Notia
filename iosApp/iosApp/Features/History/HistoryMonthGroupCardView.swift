import SwiftUI

struct HistoryMonthGroupCardView: View {
    let items: [HistoryItem]

    var body: some View {
        VStack(spacing: NotiaSpacing.s) {
            ForEach(items) { item in
                HistoryRowView(item: item)
            }
        }
        .padding(.vertical, NotiaSpacing.s)
        .background(
            RoundedRectangle(cornerRadius: NotiaSpacing.l)
                .fill(Color.Notia.surface)
        )
    }
}
