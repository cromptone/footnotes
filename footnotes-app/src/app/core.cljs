(ns app.core (:require [reagent.dom :as r]))

(defn view []
  [:p "Footnotes app"])

(defn start []
  (r/render [view]
    (.getElementById js/document "app")))

(defn init []
  (start))
