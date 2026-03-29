import SwiftUI

struct OnboardingPagerIndicator: View {
    let pageCount: Int
    let currentPage: Int

    var body: some View {
        HStack(spacing: NotiaSpacing.xs) {
            ForEach(0..<pageCount, id: \.self) { index in
                let isSelected = index == currentPage
                Capsule()
                    .fill(isSelected ? Color.Notia.primary : Color.Notia.outline.opacity(0.3))
                    .frame(width: isSelected ? 24 : 8, height: NotiaSpacing.xs)
                    .animation(.easeInOut(duration: 0.2), value: currentPage)
            }
        }
        .padding(.vertical, NotiaSpacing.l)
    }
}
