import React from "react";

export default function MotivationalQuoteBox(props) {
  return (
      <div>
          <div className="card">
              <p class="card-text"> Today's Quote: {props.value}.</p>
        </div>
      </div>

  );
}