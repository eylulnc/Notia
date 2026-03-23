import SwiftUI

struct TodayFilledStateView: View {
    let focusText: String
    let onEdit: () -> Void
    let onClear: () -> Void

    var body: some View {
        VStack(spacing: NotiaSpacing.xl) {
            Text(focusText)
                .font(.system(size: NotiaFontSizes.midTitle, weight: .semibold))
                .multilineTextAlignment(.center)
                .foregroundColor(Color.Notia.text)

            HStack(spacing: NotiaSpacing.xl) {
                Button(action: onEdit) {
                    HStack(spacing: NotiaSpacing.xs) {
                        Image(systemName: NotiaIcons.Today.edit)
                        Text("today_edit")
                    }
                    .font(.system(size: NotiaFontSizes.body, weight: .medium))
                    .foregroundColor(Color.Notia.text.opacity(0.8))
                }

                Button(action: onClear) {
                    HStack(spacing: NotiaSpacing.xs) {
                        Image(systemName: NotiaIcons.Today.clear)
                        Text("today_clear")
                    }
                    .font(.system(size: NotiaFontSizes.body, weight: .medium))
                    .foregroundColor(Color.Notia.accent)
                }
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .padding(NotiaSpacing.xxl)
        .background(Color.Notia.background)
    }
}
