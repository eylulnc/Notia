import SwiftUI

struct OnboardingView: View {
    let onFinish: () -> Void

    @State private var currentPage = 0

    var body: some View {
        TabView(selection: $currentPage) {
            OnboardingIntroPage(
                currentPage: currentPage,
                onNext: { withAnimation { currentPage = 1 } },
                onSkip: onFinish
            )
            .tag(0)

            OnboardingFreshStartPage(
                currentPage: currentPage,
                onNext: { withAnimation { currentPage = 2 } },
                onBack: { withAnimation { currentPage = 0 } },
                onSkip: onFinish
            )
            .tag(1)

            OnboardingGetStartedPage(
                currentPage: currentPage,
                onFinish: onFinish,
                onBack: { withAnimation { currentPage = 1 } }
            )
            .tag(2)
        }
        .tabViewStyle(.page(indexDisplayMode: .never))
        .background(Color.Notia.background.ignoresSafeArea())
    }
}
