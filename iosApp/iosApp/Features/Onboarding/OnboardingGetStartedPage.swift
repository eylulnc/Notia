import SwiftUI

struct OnboardingGetStartedPage: View {
    let currentPage: Int
    let onFinish: () -> Void
    let onBack: () -> Void

    var body: some View {
        VStack {
            HStack {
                NotiaTextButton("onboarding_back", action: onBack)
                Spacer()
            }

            Spacer()

            VStack(spacing: 0) {
                Text("onboarding_start_title")
                    .font(.system(size: NotiaFontSizes.midTitle, weight: .bold))
                    .foregroundColor(Color.Notia.outline)
                    .multilineTextAlignment(.center)
                    .padding(.bottom, NotiaSpacing.xxl)

                Text("onboarding_start_body")
                    .font(.system(size: NotiaFontSizes.bodyLarge))
                    .foregroundColor(Color.Notia.outline)
                    .multilineTextAlignment(.center)
                    .padding(.bottom, NotiaSpacing.l)

                OnboardingPagerIndicator(pageCount: 3, currentPage: currentPage)
            }

            Spacer()

            VStack(spacing: 0) {
                NotiaSecondaryButton("onboarding_get_started", action: onFinish)
                NotiaTextButton("onboarding_skip_to_app", action: onFinish)
                    .frame(maxWidth: .infinity)
                    .padding(.top, NotiaSpacing.s)
            }
            .padding(.bottom, NotiaSpacing.xl)
        }
        .padding(.horizontal, NotiaSpacing.xl)
    }
}
