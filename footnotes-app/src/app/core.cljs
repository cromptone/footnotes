(ns app.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.dom :as r]
            [reagent.ratom :as ratom]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(def text (ratom/atom ""))
(def url "http://localhost:5000/parse/hard_coded_query_string")

(defn get-text! []
  (go (let [response (<! (http/get url {:with-credentials? false}))]
        (reset! text (:body response)))))

(defn view []
  (get-text!)
  [:p @text])

(defn start []
  (r/render [view]
    (.getElementById js/document "app")))

(defn init []
  (start))
