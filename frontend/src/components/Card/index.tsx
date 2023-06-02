import { formatter } from "../../util/priceFormatter";
import "./styles.css";

interface CardProps {
  price: number;
  title: string;
  image: string;
}

const Card = ({ price, image, title }: CardProps) => {
  return (
    <div className="card">
      <img src={image} alt={`Food image of ${title}`} />
      <h2>{title}</h2>
      <p>
        <b>Valor: {formatter.format(price)}</b>
      </p>
    </div>
  );
};

export default Card;
