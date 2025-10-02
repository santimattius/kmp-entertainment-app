import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController(nativeViewFactory: IOSNativeView.shared)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .edgesIgnoringSafeArea(.top)
    }
}

class IOSNativeView : NativeViewFactory{
    func createWebView(urlString: String) -> UIViewController {
        let webView = WebView(url: urlString)
        return UIHostingController(rootView: webView)
    }
    
    static let shared = IOSNativeView()
}



