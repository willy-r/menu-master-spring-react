import Card from "./components/Card";
import { useFoodData } from "./hooks/useFoodData";

import "./assets/styles/App.css";

const App = () => {
  const { data } = useFoodData();

  return (
    <div className="container">
      <h1>
        Menu<span>Master</span>
      </h1>
      <div className="card-grid">
        {data?.map((foodData) => (
          <Card
            key={foodData.id}
            price={foodData.price}
            title={foodData.title}
            image={foodData.image}
          />
        ))}
      </div>
    </div>
  );
};

export default App;
