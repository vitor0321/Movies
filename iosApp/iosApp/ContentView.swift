import UIKit
import SwiftUI
import ComposeApp

struct ContentView: View {
    @Environment(\.colorScheme) var colorScheme

    var body: some View {
        ComposeView(isDarkTheme: colorScheme == .dark)
            .ignoresSafeArea(.keyboard)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    var isDarkTheme: Bool

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(isDarkTheme: isDarkTheme)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        if let mainViewController = uiViewController as? UIViewController {
            MainViewControllerKt.updateTheme(controller: mainViewController, isDarkTheme: isDarkTheme)
        }
    }
}



