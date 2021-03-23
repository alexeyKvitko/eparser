export class CategoryModel {
  categoryId: number;
  depth: number;
  parentId: number;
  categoryCode: string;
  categoryName: string
  categoryImage: string;
  node: boolean;
  childs : CategoryModel[];
}
