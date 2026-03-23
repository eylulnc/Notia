import SwiftUI

struct NotiaPrimaryButton: View {
    let title: LocalizedStringKey
    let enabled: Bool
    let action: () -> Void

    init(_ title: LocalizedStringKey, enabled: Bool = true, action: @escaping () -> Void) {
        self.title = title
        self.enabled = enabled
        self.action = action
    }

    var body: some View {
        Button(action: action) {
            Text(title)
                .font(.system(size: NotiaFontSizes.button, weight: .semibold))
                .frame(maxWidth: .infinity)
                .frame(height: NotiaSpacing.buttonHeight)
                .background(enabled ? Color.Notia.primary : Color.Notia.primary.opacity(0.5))
                .foregroundColor(.white)
                .cornerRadius(12)
        }
        .disabled(!enabled)
    }
}

struct NotiaSecondaryButton: View {
    let title: LocalizedStringKey
    let action: () -> Void

    init(_ title: LocalizedStringKey, action: @escaping () -> Void) {
        self.title = title
        self.action = action
    }

    var body: some View {
        Button(action: action) {
            Text(title)
                .font(.system(size: NotiaFontSizes.button, weight: .semibold))
                .frame(maxWidth: .infinity)
                .frame(height: NotiaSpacing.buttonHeight)
                .background(Color.Notia.surfaceVariant)
                .foregroundColor(Color.Notia.text)
                .cornerRadius(12)
        }
        .padding(.horizontal, NotiaSpacing.l)
    }
}

struct NotiaTextButton: View {
    let title: LocalizedStringKey
    let action: () -> Void

    init(_ title: LocalizedStringKey, action: @escaping () -> Void) {
        self.title = title
        self.action = action
    }

    var body: some View {
        Button(action: action) {
            Text(title)
                .font(.system(size: NotiaFontSizes.button))
                .foregroundColor(Color.Notia.text.opacity(0.7))
        }
    }
}
