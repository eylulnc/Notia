//
//  StreakPill.swift
//  iosApp
//
//  Created by Eylul Naz Can on 1.03.2026.
//

import SwiftUI


struct StreakPill: View {
    let streak: Int

    var body: some View {
        HStack(spacing: NotiaSpacing.xs) {
            Text("\(streak)")
                .font(.system(size: NotiaFontSizes.body))

            Image(systemName: "flame.fill")
                .font(.system(size: NotiaFontSizes.body))
        }
        .padding(.horizontal, NotiaSpacing.s)
        .padding(.vertical, NotiaSpacing.xs)
        .background(
            Color.Notia.primary.opacity(0.06)
        )
        .foregroundColor(Color.Notia.primary)
        .clipShape(Capsule())
    }
}
