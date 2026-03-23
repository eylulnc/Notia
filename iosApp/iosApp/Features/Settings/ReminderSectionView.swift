import SwiftUI

struct ReminderSectionView: View {
    let isEnabled: Bool
    let reminderHour: Int
    let reminderMinute: Int
    let onToggle: (Bool) -> Void
    let onTimeChanged: (Int, Int) -> Void

    var body: some View {
        SettingsGroupView(title: "settings_reminder") {
            VStack(spacing: NotiaSpacing.l) {
                HStack {
                    Text("settings_reminder_daily")
                        .font(.system(size: NotiaFontSizes.body))
                        .foregroundColor(Color.Notia.text)
                    Spacer()
                    Toggle("", isOn: Binding(
                        get: { isEnabled },
                        set: { onToggle($0) }
                    ))
                    .labelsHidden()
                    .tint(Color.Notia.primary)
                }

                if isEnabled {
                    Divider()
                        .overlay(Color.Notia.outline.opacity(0.2))

                    HStack {
                        Text("settings_reminder_time")
                            .font(.system(size: NotiaFontSizes.body))
                            .foregroundColor(Color.Notia.text)
                        Spacer()
                        DatePicker(
                            "",
                            selection: timeBinding,
                            displayedComponents: .hourAndMinute
                        )
                        .labelsHidden()
                        .tint(Color.Notia.primary)
                    }
                }
            }
        }
    }

    private var timeBinding: Binding<Date> {
        Binding(
            get: {
                var comps = DateComponents()
                comps.hour = reminderHour
                comps.minute = reminderMinute
                return Calendar.current.date(from: comps) ?? Date()
            },
            set: { date in
                let comps = Calendar.current.dateComponents([.hour, .minute], from: date)
                onTimeChanged(comps.hour ?? 8, comps.minute ?? 30)
            }
        )
    }
}
