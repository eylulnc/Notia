import SwiftUI

struct AppearanceSectionView: View {
    let selected: ThemeMode
    let onSelect: (ThemeMode) -> Void

    var body: some View {
        SettingsGroupView(title: "settings_appearance") {
            ThemeSegmentedControl(selected: selected, onSelect: onSelect)
        }
    }
}

private struct ThemeSegmentedControl: View {
    let selected: ThemeMode
    let onSelect: (ThemeMode) -> Void

    var body: some View {
        HStack(spacing: NotiaSpacing.xs) {
            ForEach(ThemeMode.allCases, id: \.self) { mode in
                let isSelected = mode == selected
                Button(action: { onSelect(mode) }) {
                    Text(mode.displayName)
                        .font(.system(size: NotiaFontSizes.label, weight: .medium))
                        .foregroundColor(isSelected ? Color.Notia.text : Color.Notia.outline)
                        .frame(maxWidth: .infinity)
                        .padding(.vertical, NotiaSpacing.s)
                        .background(
                            RoundedRectangle(cornerRadius: NotiaSpacing.s)
                                .fill(isSelected ? Color.Notia.background : Color.clear)
                        )
                }
            }
        }
        .padding(NotiaSpacing.xs)
        .background(
            RoundedRectangle(cornerRadius: NotiaSpacing.m)
                .fill(Color.Notia.outline.opacity(0.15))
        )
    }
}
