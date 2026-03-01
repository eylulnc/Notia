//
//  TodayEmptyState.swift
//  iosApp
//
//  Created by Eylul Naz Can on 1.03.2026.
//

import SwiftUI


struct TodayEmptyStateView: View {
    let onSetFocus: () -> Void
    
    var body: some View {
        VStack(spacing: NotiaSpacing.xxl) {
            Text("What is your focus for today?")
                .font(.system(size: NotiaFontSizes.midTitle, weight: .semibold))
                .multilineTextAlignment(.center)
                .foregroundColor(.primary)
            
            Button(action: onSetFocus) {
                Text("Set Focus")
                    .font(.system(size: NotiaFontSizes.button))
                    .foregroundColor(.white)
                    .padding(.horizontal, NotiaSpacing.xl)
                    .padding(.vertical, NotiaSpacing.m)
                    .background(.primary)
                    .cornerRadius(8)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .padding(NotiaSpacing.xxl)
    }
}
