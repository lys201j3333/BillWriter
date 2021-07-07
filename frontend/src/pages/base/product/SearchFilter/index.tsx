import { Input } from 'antd';
import styles from '@/styles/search-filter.less';
import {  DatePicker,Space} from 'antd';
import moment from 'moment';
interface SearchFilterProps {
  onSearch: () => void;
  searchProps: defs.ProductQueryDTO;
  changeSearchProps: (searchProps: defs.ProductQueryDTO) => void;
}
const { RangePicker } = DatePicker;
export default function SearchFilter(props: SearchFilterProps) {
  return (
    <div className={styles.filterPanel}>
      <div className={styles.filterItem}>
        <span className={styles.label}>关键词：</span>
        <Input.Search
          allowClear={true}
          value={props.searchProps.pproduct}
          onSearch={props.onSearch}
          onChange={(e) =>
            props.changeSearchProps({
              pproduct: e.target.value,
            })
          }
        />
      </div>
    </div>
  );
}
