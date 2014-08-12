(defproject hashtest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [om "0.7.1"]
                 [compojure "1.1.8"]
                 [hiccup "1.0.5"]
                 [sablono "0.2.21"]
                 [ring/ring-core "1.3.0"]
                 [ring/ring-jetty-adapter "1.3.0"]]
  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [lein-ring "0.8.11"]]
  :ring {:handler hashtest.core/app}
  :cljsbuild {:builds
              [{:id "advanced"
                :source-paths ["src-cljs"]
                :compiler {:output-to "resources/public/main.js"
                           :optimizations :advanced
                           :preamble ["react/react.min.js"]
                           :externs ["react/externs/react.js"]}}]})
