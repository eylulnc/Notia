import SwiftUI

struct HistoryMonthHeaderView: View {
    let label: String

    var body: some View {
        HStack(spacing: NotiaSpacing.m) {
            Text(label.uppercased())
                .font(.system(size: NotiaFontSizes.label))
                .foregroundColor(Color.Notia.outline)
                .tracking(1.5)

            Rectangle()
                .fill(Color.Notia.outline.opacity(0.2))
                .frame(height: 1)
        }
        .padding(.top, NotiaSpacing.l)
        .padding(.bottom, NotiaSpacing.s)
    }
}
