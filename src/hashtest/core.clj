(ns hashtest.core 
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [hiccup.page :refer [html5 include-js]]))

(defn index [req]
  (html5
    [:h1 "cljs hash test"]
    [:div (get (:headers req) "user-agent")]
    [:div {:id "app"}]
    (include-js "main.js")))


(defroutes app
  (GET "/" req (index req))
  (route/resources "/" ))
