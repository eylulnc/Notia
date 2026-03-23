import SwiftUI

struct HistoryEmptyStateView: View {

    var body: some View {
        Text("history_empty")
            .font(.system(size: NotiaFontSizes.body))
            .foregroundColor(Color.Notia.outline)
            .multilineTextAlignment(.center)
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .padding(NotiaSpacing.xl)
            .background(
                RoundedRectangle(cornerRadius: NotiaSpacing.l)
                    .fill(Color.Notia.surface)
                    .overlay(
                        RoundedRectangle(cornerRadius: NotiaSpacing.l)
                            .stroke(Color.Notia.outline.opacity(0.2), lineWidth: 1)
                    )
            )
            .padding(NotiaSpacing.xl)
    }
}
