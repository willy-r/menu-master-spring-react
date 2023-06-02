import { useQuery } from "@tanstack/react-query";

import { getFoods } from "../services/foods.api";

export const useFoodData = () => {
  const query = useQuery({
    queryFn: getFoods,
    queryKey: ["food-data"],
    retry: 2,
  });

  return {
    ...query,
    data: query.data?.data
  }
};
