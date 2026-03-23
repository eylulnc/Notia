import SwiftUI

struct TodayFilledStateView: View {
    let focusText: String
    let onEdit: () -> Void
    let onClear: () -> Void
    
    var body: some View {
        ScrollView {
            VStack(spacing: NotiaSpacing.xl) {
                Text(focusText)
                    .font(.system(size: NotiaFontSizes.midTitle, weight: .semibold))
                    .multilineTextAlignment(.center)
                    .foregroundColor(Color.Notia.text)
                    .padding(.horizontal, NotiaSpacing.l)
                
                HStack(spacing: NotiaSpacing.xl) {
                    Button(action: onEdit) {
                        HStack(spacing: NotiaSpacing.xs) {
                            Image(systemName: "pencil")
                            Text("Edit")
                        }
                        .font(.system(size: NotiaFontSizes.body, weight: .medium))
                        .foregroundColor(Color.Notia.text.opacity(0.8))
                    }
                    
                    Button(action: onClear) {
                        HStack(spacing: NotiaSpacing.xs) {
                            Image(systemName: "trash")
                            Text("Clear")
                        }
                        .font(.system(size: NotiaFontSizes.body, weight: .medium))
                        .foregroundColor(Color.Notia.accent) // Using accent for clear/delete
                    }
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.top, NotiaSpacing.xxl)
        }
        .background(Color.Notia.background)
    }
}
