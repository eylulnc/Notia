import SwiftUI

struct OnboardingIntroPage: View {
    let currentPage: Int
    let onNext: () -> Void
    let onSkip: () -> Void

    var body: some View {
        VStack {
            HStack {
                Spacer()
                NotiaTextButton("onboarding_skip", action: onSkip)
            }

            Spacer()

            VStack(spacing: 0) {
                Text("onboarding_intro_title")
                    .font(.system(size: NotiaFontSizes.largeTitle, weight: .bold))
                    .foregroundColor(Color.Notia.text)
                    .multilineTextAlignment(.center)
                    .padding(.bottom, NotiaSpacing.m)

                Text("onboarding_intro_body")
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
