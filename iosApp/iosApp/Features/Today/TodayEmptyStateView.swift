import SwiftUI

struct TodayEmptyStateView: View {
    let onSetFocus: () -> Void
    
    var body: some View {
        VStack(spacing: NotiaSpacing.xxl) {
            Text("What is your focus for today?")
                .font(.system(size: NotiaFontSizes.midTitle, weight: .semibold))
                .multilineTextAlignment(.center)
                .foregroundColor(Color.Notia.text)
            
            NotiaPrimaryButton("Set Focus", action: onSetFocus)
                .padding(.horizontal, NotiaSpacing.xl)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .padding(NotiaSpacing.xxl)
        .background(Color.Notia.background)
    }
}
