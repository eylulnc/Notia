import SwiftUI

struct TodayEmptyStateView: View {
    let onSetFocus: () -> Void
    
    var body: some View {
        VStack(spacing: NotiaSpacing.xxl) {
            Text("today_question")
                .font(.system(size: NotiaFontSizes.midTitle, weight: .semibold))
                .multilineTextAlignment(.center)
                .foregroundColor(Color.Notia.text)

            NotiaPrimaryButton("today_set_focus", action: onSetFocus)
                .padding(.horizontal, NotiaSpacing.xl)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .padding(NotiaSpacing.xxl)
        .background(Color.Notia.background)
    }
}
