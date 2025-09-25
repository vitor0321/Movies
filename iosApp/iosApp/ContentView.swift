import AppMan
import UIKit
import SwiftUI

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
        MainViewControllerKt.mainViewController(isDarkTheme: isDarkTheme) as! UIViewController
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        if let mainViewController = uiViewController as? UIViewController {
            MainViewControllerKt.updateTheme(controller: mainViewController, isDarkTheme: isDarkTheme)
        }
    }
}
