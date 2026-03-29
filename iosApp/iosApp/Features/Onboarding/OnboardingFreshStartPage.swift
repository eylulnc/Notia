import SwiftUI

struct OnboardingFreshStartPage: View {
    let currentPage: Int
    let onNext: () -> Void
    let onBack: () -> Void
    let onSkip: () -> Void

    private let previewItems: [HistoryItem] = [
        HistoryItem(dateLabel: "Mon", dayAbbrev: "Mon", dayNumber: "1", focusText: "Take a deep breath and relax")
    ]

    var body: some View {
        VStack {
            HStack {
                NotiaTextButton("onboarding_back", action: onBack)
                Spacer()
                NotiaTextButton("onboarding_skip", action: onSkip)
            }

            Spacer()

            VStack(spacing: 0) {
                Text("onboarding_fresh_title")
                    .font(.system(size: NotiaFontSizes.title, weight: .bold))
                    .foregroundColor(Color.Notia.text)
                    .multilineTextAlignment(.center)
                    .padding(.bottom, NotiaSpacing.m)

                RoundedRectangle(cornerRadius: NotiaSpacing.l)
                    .fill(Color.Notia.surfaceVariant)
                    .frame(height: 160)
                    .overlay(
                        HistoryMonthGroupCardView(items: previewItems)
                            .padding(NotiaSpacing.m)
                    )
                    .padding(.vertical, NotiaSpacing.m)

                Text("onboarding_fresh_body")
                    .font(.system(size: NotiaFontSizes.bodyLarge))
                    .foregroundColor(Color.Notia.outline)
                    .multilineTextAlignment(.center)
                    .padding(.bottom, NotiaSpacing.xxl)

                OnboardingPagerIndicator(pageCount: 3, currentPage: currentPage)
            }

            Spacer()

            NotiaPrimaryButton("onboarding_next", action: onNext)
                .padding(.bottom, NotiaSpacing.xl)
        }
        .padding(.horizontal, NotiaSpacing.xl)
    }
}
