//
//  NotiaTopBar.swift
//  iosApp
//
//  Created by Eylul Naz Can on 1.03.2026.
//

import SwiftUI


struct NotiaTopBar<Leading: View, Trailing: View>: View {

    let title: LocalizedStringKey
    let leading: Leading?
    let trailing: Trailing?

    init(
        title: LocalizedStringKey,
        @ViewBuilder leadingContent: () -> Leading? = { nil },
        @ViewBuilder trailingContent: () -> Trailing? = { nil }
    ) {
        self.title = title
        self.leading = leadingContent()
        self.trailing = trailingContent()
    }

    var body: some View {
        HStack {
            // Leading
            ZStack(alignment: .leading) {
                leading
            }
            .frame(width: 60, height: 44)

            // Title
            Text(title)
                .textCase(.uppercase)
                .font(.system(size: NotiaFontSizes.label))
                .foregroundColor(Color.Notia.outline)
                .frame(maxWidth: .infinity)
                .multilineTextAlignment(.center)

            // Trailing
            ZStack(alignment: .trailing) {
                trailing
            }
            .frame(width: 44, height: 44)
        }
        .padding(.horizontal, NotiaSpacing.l)
        .padding(.top, NotiaSpacing.l)
        .frame(height: 64)
        .background(Color.Notia.background)
    }
}
