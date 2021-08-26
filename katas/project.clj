(defproject katas.clj "0.1.0-SNAPSHOT"
  :description "kata practice"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "3.1.0"]]
  :profiles {:dev {:dependencies [[speclj "3.3.2"]]}}
  :plugins [[speclj "3.3.2"]]
  :test-paths ["spec"])

