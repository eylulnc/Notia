import SwiftUI

struct HistoryStreakSummaryView: View {
    let currentStreak: Int
    let longestStreak: Int

    var body: some View {
        HStack(spacing: NotiaSpacing.l) {
            StreakStatCard(label: "history_current_streak", value: currentStreak)
            StreakStatCard(label: "history_longest_streak", value: longestStreak)
        }
        .padding(.horizontal, NotiaSpacing.l)
        .padding(.vertical, NotiaSpacing.m)
    }
}

private struct StreakStatCard: View {
    let label: LocalizedStringKey
    let value: Int

    var body: some View {
        VStack(spacing: NotiaSpacing.s) {
            Text(label)
                .textCase(.uppercase)
                .font(.system(size: NotiaFontSizes.caption, weight: .semibold))
                .foregroundColor(Color.Notia.outline)
                .multilineTextAlignment(.center)
                .tracking(1.5)

            HStack(alignment: .lastTextBaseline, spacing: NotiaSpacing.xs) {
                Text("\(value)")
                    .font(.system(size: NotiaFontSizes.subTitle, weight: .bold))
                    .foregroundColor(Color.Notia.text)
                Text("history_days")
                    .font(.system(size: NotiaFontSizes.caption))
                    .foregroundColor(Color.Notia.text)
            }
        }
        .frame(maxWidth: .infinity)
        .padding(NotiaSpacing.l)
        .background(
            RoundedRectangle(cornerRadius: NotiaSpacing.l)
                .fill(Color.Notia.surface)
                .overlay(
                    RoundedRectangle(cornerRadius: NotiaSpacing.l)
                        .stroke(Color.Notia.outline.opacity(0.2), lineWidth: 1)
                )
        )
    }
}
