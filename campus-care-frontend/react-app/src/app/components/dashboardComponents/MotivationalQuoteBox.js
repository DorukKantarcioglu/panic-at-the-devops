import React, {useEffect, useState} from "react";
import ReservationService from "../../../service/ReservationService";
import NotificationService from "../../../service/NotificationService";

export default function MotivationalQuoteBox(props) {

    const [motivationalQuote, setMotivationalQuote] = useState();

    useEffect(async () => {
        await fetchMotivationalQuote()
    })
    async function fetchMotivationalQuote(){
    const response = await NotificationService.getRandomMotivationalQuote();
    {
        setMotivationalQuote(response.content);
    }
}
  return (
    <div>
      <div className="card" style={{}}>
        <p class="card-text"> Today's Quote: {motivationalQuote}</p>
      </div>
    </div>
  );
}
