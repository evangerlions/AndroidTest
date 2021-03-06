package com.example.homework4;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */

public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView newsTitleListView;

    private List<News> newsList;

    private NewsAdapter adapter;

    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews(); //初始化新闻数据
        adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true; //可以找到news_content_layout布局时，为双页模式
        } else {
            isTwoPane = false; //找不到news_content_layout布局时，为单页模式
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);

        if (isTwoPane) {
            //如果是双页模式，则刷新NewsContentFragment中的内容
            NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(), news.getContent());
        } else {
            //如果单页模式，就直接启动NewsContentActivity
            NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
        }
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("中国特色社会主义进入新时代判断具划时代意义");
        news1.setContent("新华社北京10月18日消息，马凯、刘奇葆、孙春兰、李建国、李源潮、张春贤、孟建柱、赵乐际、胡春华、杜青林、赵洪祝、杨晶、王胜俊、王晨、沈跃跃、吉炳轩、张平、王勇、周强、陈元、卢展工、周小川、马飚同志18日分别参加所在的十九大代表团讨论。他们指出，党的十九大报告高举旗帜、立论定向，把握大势、总揽全局，作出了“中国特色社会主义进入新时代”的重大判断，提出了具有全局性、战略性、前瞻性的行动纲领，具有划时代的里程碑意义。\n" +
                "\n" +
                "　　马凯同志在参加甘肃代表团讨论时说，党的十九大报告站位高远、主题鲜明、思想深邃、气势恢宏，是实现中华民族伟大复兴中国梦的行动纲领，我完全赞同和拥护。报告是坚持和运用辩证唯物主义、历史唯物主义的光辉典范，以历史的眼光、全球的视野，科学判断时代，明确历史使命，既符合实际，又振奋人心。特别是在充分肯定党的十八大以来取得历史性成就的同时，又清醒深刻地指出了面临的困难与挑战，强调初级阶段的基本国情没有变，强调仍然要以经济建设为中心，把发展作为解决我国一切问题的基础和关键。只要我们始终不渝坚持以习近平同志为核心的党中央的领导，坚定不移走中国特色社会主义道路，牢固树立和贯彻落实新发展理念，任何困难都可以克服，任何挑战都可以战胜。\n" +
                "\n" +
                "　　刘奇葆同志在参加江西代表团讨论时说，党的十九大报告高举旗帜、立论定向，把握大势、总揽全局，是当前和今后一个时期党和国家事业的总设计、总定向、总指引，为新时代中国共产党人立起了新的历史坐标，为马克思主义注入了新的真理力量，为中国特色社会主义事业提供了新的战略指引，是一篇光辉的马克思主义纲领性文献。报告凝结着一个高度共识，就是过去五年以习近平同志为核心的党中央带领全党全国人民取得历史性成就、党和国家事业发生历史性变革；报告贯穿着一条鲜明主线，就是新时代坚持和发展中国特色社会主义；报告闪耀着一个思想灵魂，就是新时代中国特色社会主义思想；报告凸显着一个根本要求，就是全面加强党的领导、全面从严治党。\n" +
                "\n" +
                "　　孙春兰同志在参加吉林代表团讨论时说，习近平同志的报告主题鲜明、博大精深，是举旗定向、引领复兴、兴党强国的马克思主义光辉文献，完全赞成、衷心拥护。习近平新时代中国特色社会主义思想是报告的红线和灵魂，作为党的指导思想写到党的旗帜上，是报告最大亮点和最重要的历史贡献，为我们党迈进新时代、开启新征程、续写新篇章提供了行动指南。报告提出的一系列重要思想、重要观点、重大判断、重大举措，对于夺取新时代中国特色社会主义伟大胜利具有重大而深远的意义。相信有以习近平同志为核心的党中央坚强领导，有习近平新时代中国特色社会主义思想科学指导，有习近平总书记这位领袖、核心掌舵领航，党和国家事业必将乘风破浪、无往而不胜。\n" +
                "\n" +
                "　　李建国同志在河北代表团讨论时说，我完全拥护习近平同志作的十九大报告。报告有一个鲜明主题，开宗明义是不忘初心，牢记使命。为人民谋幸福、为民族谋复兴，是我们党的初心和使命。党的十八大一闭幕，习近平总书记就宣示，人民对美好生活的向往就是我们的奋斗目标，并发出实现中华民族伟大复兴中国梦的伟大号召。五年来我们党遵循这一主旨砥砺奋进。五年来的历史性成就和历史性变革推动中国特色社会主义进入新时代。我们要始终不忘初心，承担起新时代中国共产党的历史使命，永远坚持以人民为中心。我坚信，有习近平总书记作为党中央和全党的核心，有习近平新时代中国特色社会主义思想的指引，我们一定能够实现民族复兴的伟大梦想。\n" +
                "\n" +
                "　　李源潮同志在参加青海代表团讨论时说，习近平同志作的报告高举旗帜、继往开来，给人以信仰的感召、方向的指引、进取的力量、胜利的信心，是指引中国特色社会主义事业迈进新时代、谱写新篇章的总纲领、总部署、总动员。党的十八大以来，以习近平同志为核心的党中央以巨大的政治勇气和强烈的责任担当，推动党和国家事业发生历史性变革，中国特色社会主义进入新时代。习近平总书记提出一系列新理念新思想新战略，创立新时代中国特色社会主义思想，使马克思主义中国化进入新境界。报告关于我国社会主要矛盾的论述是重大的政治判断和理论创新，提出的后30年分两步走建设社会主义现代化强国的奋斗目标鼓舞人心，也为全世界所有希望从社会主义道路通向现代化的民族和人们树起了一面令人向往的旗帜。\n" +
                "\n" +
                "　　张春贤同志在参加湖北代表团讨论时说，习近平同志作的报告，作出了“中国特色社会主义进入新时代”的重大判断，提出了全局性、战略性、前瞻性很强的行动纲领，明确了中华民族伟大复兴的时间表和路线图，具有划时代的里程碑意义。习近平新时代中国特色社会主义思想，是马克思主义中国化最新成果，是全党全国人民为实现中华民族伟大复兴而奋斗的行动指南，必须长期坚持。党的十八大以来所取得的历史性成就、发生的历史性变革，根本在于以习近平同志为核心的党中央的坚强领导。我们必须牢固树立“四个意识”，坚决维护以习近平同志为核心的党中央权威和集中统一领导，持之以恒用习近平新时代中国特色社会主义思想武装全党，努力夺取新时代中国特色社会主义伟大胜利。\n" +
                "\n" +
                "　　孟建柱同志在参加河南代表团讨论时说，习近平同志作的报告是登高望远、举旗定向、谋篇布局的好报告，具有非凡的战略高度、鲜明的理论深度和强大的真理力量，是迈进新时代、开启新征程的政治宣言和行动指南。最显著的特点是站在中国特色社会主义新时代的历史方位上，不忘本来、吸收外来、面向未来，具有很强的继承性、创新性、时代性。最重大的贡献是科学提出了新时代中国特色社会主义思想，开辟了马克思主义中国化新境界、中国特色社会主义新境界，是前进的灯塔。最突出的亮点是深刻回答了新时代坚持和发展中国特色社会主义的全局性方向性问题，明确了党和国家未来发展的根本任务、战略安排。学习十九大精神，要把思想和行动统一起来，谱写社会主义现代化建设恢宏新篇章。\n" +
                "\n" +
                "　　赵乐际同志在参加黑龙江代表团讨论时说，习近平同志作的报告，是一个举旗定向、引领复兴、兴党强国、气势恢宏的好报告，是我们党迈进新时代、开启新征程、续写新篇章的政治宣言、行动指南。5年来，我国改革开放和社会主义现代化建设取得历史性成就、发生历史性变革，根本的是以习近平同志为核心的党中央坚强领导，是习近平新时代中国特色社会主义思想科学指引。我们要深刻领会报告提出的重要思想、重要观点，作出的重大判断、重大部署，深入学习贯彻习近平新时代中国特色社会主义思想，牢固树立“四个意识”，坚定“四个自信”，自觉维护以习近平同志为核心的党中央权威和集中统一领导，不忘初心、牢记使命，锐意进取、埋头苦干，把十九大确定的宏伟蓝图和各项任务落到实处。\n" +
                "\n" +
                "　　胡春华同志在参加广东代表团讨论时说，习近平同志作的十九大报告，是我们党迈进新时代、开启新征程、续写新篇章的政治宣言和行动指南。报告把实现中华民族伟大复兴的中国梦作为奋斗目标，实现了党的奋斗目标与国家民族前途命运的高度统一。作出中国特色社会主义进入新时代的重大判断，阐明我国社会主要矛盾的深刻变化，明确了党和国家事业所处的历史方位，与社会主义初级阶段判断一样具有划时代意义。确立新时代中国特色社会主义思想为党的指导思想，为实现社会主义现代化和中华民族伟大复兴提供了理论指引和根本遵循，是中国特色社会主义理论一次里程碑式的伟大飞跃。报告关于社会主义现代化建设“两个15年”的总体设计，将与“三步走”战略部署一样产生重大而深远的影响。广东将深入贯彻十九大精神，以新时代中国特色社会主义思想为指引，不断开创改革发展新局面。\n" +
                "\n" +
                "　　杜青林同志在参加天津代表团讨论时说，习近平同志所作的报告，是一篇闪耀着马克思主义真理光辉的纲领性文献，紧扣中国特色社会主义新时代的主题。这个新时代，科学标定了我国发展新的历史方位和时代坐标，开启了坚持和发展中国特色社会主义的新纪元，充分表明了五年来的历史性成就、历史性变革，明确宣示了我们党肩负的重大历史使命，鲜明确立了习近平新时代中国特色社会主义思想这个伟大思想旗帜、行动纲领，全面揭示了党和国家未来发展的根本方向和战略指向。成功迈入新时代，根本在于习近平总书记领袖核心的掌舵领航、党中央的坚强领导、习近平新时代中国特色社会主义思想的科学指引、全党全国各族人民的砥砺奋进。\n" +
                "\n" +
                "　　赵洪祝同志在参加山西代表团讨论时说，完全赞成习近平同志作的报告。报告旗帜鲜明、主题突出，有着深厚实践基础、重大理论创新、长远战略谋划、鲜明人民立场、坚定责任担当，是号召全党不忘初心、牢记使命的动员令，是指引中国特色社会主义迈进新时代的行动纲领，是向世界昭告中国共产党人一定能实现中华民族伟大复兴的政治宣言。新时代体现在开启新征程上，体现在继承、丰富和发展马克思主义上，体现在五年来进行的一系列实践探索和积累的宝贵经验上。伟大成就的取得，根本得益于以习近平同志为核心的党中央坚强领导，充分展示了总书记率先垂范的人格魅力，统揽全局的雄才大略，攻坚克难的顽强意志品质，爱民为民的真挚情怀，忧党兴党的责任担当。\n" +
                "\n" +
                "　　杨晶同志在参加中央国家机关代表团讨论时说，完全赞成习近平同志所作的报告。报告系统总结了十八大以来党和国家事业取得的历史性成就和发生的历史性变革，全面展示了以习近平同志为核心的党中央治国理政的高超智慧和卓越能力，宣告中国特色社会主义进入新时代，令人倍感振奋和自豪。完全赞成将习近平新时代中国特色社会主义思想确立为党的指导思想。这一重要思想是马克思主义中国化最新成果，是经受了实践检验的强大思想武器，贯穿着马克思主义群众观点和人民立场，必将有力凝聚全党全国人民投身社会主义现代化建设新征程，推动决胜全面建成小康社会、夺取新时代中国特色社会主义伟大胜利、实现中华民族伟大复兴的中国梦。\n" +
                "\n" +
                "　　王胜俊同志在参加辽宁代表团讨论时说，完全赞成和拥护习近平同志所作的大会报告。报告通篇闪耀着马克思主义真理光芒，是我们党迈进新时代、开启新征程、续写新篇章的政治宣言和行动指南，是马克思主义纲领性文献，对于我们党团结带领全国各族人民实现“两个一百年”奋斗目标和中华民族伟大复兴中国梦具有重大而深远的指导意义。认真学习贯彻党的十九大精神是当前和今后一个时期全党的首要政治任务。要按照党中央要求，认真学习贯彻党的十九大精神，尤其是要深入学习贯彻习近平新时代中国特色社会主义思想，准确把握党的十九大确立的重大判断、重大战略、重大任务、重大举措，切实把思想和行动统一到党的十九大精神上来，不断开创中国特色社会主义事业新局面。\n" +
                "\n" +
                "　　王晨同志在参加浙江代表团讨论时说，完全赞同习近平同志所作的报告。报告具有很强的全局性、时代性、战略性、指导性，是进行伟大斗争、建设伟大工程、推进伟大事业、实现伟大梦想的根本遵循和行动指南。报告所传递的中国声音，所显示的中国力量，所凝聚的中国精神，所提供的中国方案，必将在国内国际上产生重大影响。五年来党和国家事业取得历史性成就，发生历史性变革，最根本的是有习近平同志这个党中央的核心、全党的核心掌舵领航，有以习近平同志为核心的党中央坚强领导，有习近平新时代中国特色社会主义思想的科学指导。有了习近平同志这个核心，全党全军全国各族人民在新的历史征程上就有了统帅、领袖和领路人，就能不断夺取新时代中国特色社会主义新胜利。\n" +
                "\n" +
                "　　沈跃跃同志在参加福建代表团讨论时说，十九大报告是我们党理论创新、实践创新、制度创新最新成果的集中体现，是高举旗帜、推动新时代中国特色社会主义事业的政治宣言和行动纲领。十八大以来党和国家各项事业取得历史性成就、发生历史性变革，最根本的是有习近平同志这个核心，有党中央坚强领导。完全赞成将习近平新时代中国特色社会主义思想作为党的指导思想，这有划时代意义；报告提出的一系列新的重要思想重要观点重大判断重大举措，是新时代坚持和发展中国特色社会主义的根本遵循；强调坚定不移全面从严治党，毫不动摇坚持和完善党的领导，毫不动摇把党建设得更加坚强有力。要牢固树立“四个意识”，坚决维护以习近平同志为核心的党中央权威和集中统一领导，为实现中国梦而努力奋斗。\n" +
                "\n" +
                "　　吉炳轩同志在参加江苏代表团讨论时说，习近平同志所作的报告，使我们看到了我们党在以习近平同志为核心的党中央坚强领导下坚定的道路自信、理论自信、制度自信和文化自信。习近平总书记以敏锐的眼光、阔大的胸怀、高超的智慧、过人的胆略，透析并把握历史发展大势，坚持把马克思主义基本原理同中国改革发展新阶段、新形势紧密结合，坚持问题导向，尊重发展规律，始终坚持以人为本的执政理念，带领全党全国各族人民坚定不移走中国特色社会主义道路，带领中国人民为实现中华民族伟大复兴的中国梦而奋斗，使我们这个有13亿多人口的大国、8900多万党员的大党有了主心骨、顶梁柱，这是最为关键的。\n" +
                "\n" +
                "　　张平同志在参加山东代表团讨论时说，完全赞成、坚决拥护习近平同志所作的报告。报告体现了以习近平同志为核心的党中央高度的使命担当、理论勇气和实践决心，是我们党站在新的历史起点上开创未来的政治宣言。一是总结过去五年的历史性成就和历史性变革，对中国特色社会主义事业更加充满自信。二是对中国特色社会主义进入新时代的判断，明确了实现中华民族伟大复兴的战略部署。三是新时代中国特色社会主义思想为夺取党和国家事业新胜利提供了强大的思想武器和行动指南。四是坚定不移全面从严治党，为中国特色社会主义事业提供了坚强政治保证。我们要紧密团结在以习近平同志为核心的党中央周围，始终高举中国特色社会主义伟大旗帜，牢固树立“四个意识”，坚定“四个自信”，为实现“两个一百年”奋斗目标、实现中华民族伟大复兴的中国梦不懈奋斗。\n" +
                "\n" +
                "　　王勇同志在参加山东代表团讨论时说，十九大报告高举旗帜、主题鲜明，是推进中国特色社会主义迈进新时代、开创新征程、谱写新篇章的总纲领、总部署、总动员，我完全赞同、坚决拥护。党的十八大以来党和国家事业取得历史性成就和发生历史性变革，最根本在于确立了习近平总书记在党中央、全党的核心地位，形成了习近平新时代中国特色社会主义思想。十九大报告把习近平新时代中国特色社会主义思想列入党的指导思想，是全党全国各族人民的共同愿望和共同意志。要深入学习贯彻党的十九大精神，牢固树立“四个意识”，增强“四个自信”，更加紧密地团结在以习近平同志为核心的党中央周围，全力实现全面建成小康社会、夺取新时代中国特色社会主义伟大胜利。\n" +
                "\n" +
                "　　周强同志在参加上海代表团讨论时说，习近平同志所作的党的十九大报告高屋建瓴、统揽全局、气势磅礴，通篇闪耀着马克思主义的光辉，体现了全党意志和全国人民的共同愿望，是指引夺取新时代中国特色社会主义伟大胜利、实现中华民族伟大复兴的行动纲领，是推进全球治理、国际法治，构建人类命运共同体的政治宣言。党的十八大以来，党和国家事业取得历史性成就、发生历史性变革，根本在于以习近平同志为核心的党中央坚强领导，根本在于新时代中国特色社会主义思想的科学指引，根本在于习近平同志作为全党核心的掌舵领航。各级法院要深入学习贯彻党的十九大报告，始终做到维护核心、绝对忠诚、听党指挥、勇于担当，坚持以新时代中国特色社会主义思想武装头脑、指导实践、推动工作，为建设社会主义法治国家作出新的更大贡献。\n" +
                "\n" +
                "　　陈元同志在参加江苏代表团讨论时说，报告通篇闪烁着马克思主义思想光辉，是中国特色社会主义经典文献。报告主题非常鲜明，宣示了决胜全面建成小康社会、夺取新时代中国特色社会主义伟大胜利的信心决心。过去5年成绩辉煌，党和国家事业取得全方位开创性的历史成就。理论概括非常精辟，将习近平新时代中国特色社会主义思想确立为我们党的指导思想具有充分的理论基础、政治基础、实践基础、群众基础。战略部署非常科学，将有力指导我们在新的历史起点上实现中华民族伟大复兴的中国梦。报告对深化金融体制改革提出了明确要求。我们的国家财富、人民财富主要体现在金融体系中，金融工作要按照十九大报告作出的部署要求，更好服务实体经济、服务人民日益增长的美好生活需要，更好服务国家战略、服务我国参与全球化进程，不断为新时代中国特色社会主义发展全局作出新的更大贡献。\n" +
                "\n" +
                "　　卢展工同志在参加广东代表团讨论时说，习近平同志作的报告是鼓舞人心、催人奋进的好报告，我完全拥护这个报告。报告着眼于“两个一百年”奋斗目标，着眼于实现中华民族伟大复兴的中国梦，着眼于中国特色社会主义新时代，深刻阐述了新时代中国特色社会主义思想，深刻阐述了我们所处的新的历史方位，深刻阐述了我国社会主要矛盾的变化，深刻阐述了伟大斗争、伟大工程、伟大事业、伟大梦想，深刻阐述了实现我们奋斗目标的总任务、总要求，深刻阐述了全面从严治党的时代命题。党的十八大以来的五年，以习近平同志为核心的党中央以抓铁有痕、踏石留印的工作力度，以逆水行舟、逆流而上的决心和勇气，带领全党取得了历史性成就，发生历史性变革，作出了历史性的特殊贡献。\n" +
                "\n" +
                "　　周小川同志在参加中央金融系统代表团讨论时说，习近平同志所作的报告主题鲜明、思想深邃、高屋建瓴，气势恢宏，既实事求是、全面系统地总结了党的十八大以来各项工作取得的伟大成就，又作出了中国特色社会主义进入新时代的科学判断，深入阐述了我们党的历史使命，对今后的工作作出了全面部署，具有极强的思想性、战略性、指导性和动员性。整个报告鼓舞人心，催人奋进，是我们决胜建成全面小康社会、奋力夺取新时代中国特色社会主义伟大胜利、实现中华民族伟大复兴中国梦的纲领性文件，对于统一全党思想、凝聚全国人民意志，必将产生深远而又重要的影响。金融系统要在今后的工作中深入学习领会报告精神，自觉抓好贯彻落实，为经济社会平稳健康发展和全面建设社会主义现代化国家作出新的贡献。\n" +
                "\n" +
                "　　马飚同志在参加广西代表团讨论时说，完全赞成习近平同志所作的党的十九大报告，这是中国特色社会主义新时代的总纲领总部署总动员。报告指出我国进入了中国特色社会主义新时代，这是我国发展新的历史方位，具有划时代意义。我完全赞成把习近平新时代中国特色社会主义思想作为中国共产党的指导思想和行动指南。党的十八大以来，习近平总书记运用马克思主义立场观点方法对中国特色社会主义事业新实践新创造新经验作了全面系统的总结和提炼，提出了一系列治国理政新理念新思想新战略，创造性地回答了我们党新时代坚持和发展什么样的中国特色社会主义、怎样坚持和发展中国特色社会主义这个重大时代课题，是闪耀着马克思主义真理光辉的重大理论成果，是我们党必须长期坚持的指导思想。");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("中国影响力到底有多大 看看这个项目的进展");
        news2.setContent("“一带一路”倡议是中国对于解决国际问题贡献的中国智慧和中国方案。中巴经济走廊是“一带一路”倡议的旗舰项目，其发展情况一直受到全世界的关注。近几年，中巴经济走廊建设情况到底怎么样？\n" +
                "\n" +
                "　　据了解，从2015年正式开始建设到现在，无论道路、电站还是港口的建设，都在近三年取得快速的发展；这些突出的建设成绩，让巴基斯坦百姓真切感受到正在发生的、和可以预期的生活改善。而中巴经济走廊的建设也彰显着中国的影响力。\n" +
                "\n" +
                "　　月均近6000名巴基斯坦人前往中国\n" +
                "\n" +
                "　　根据中国驻巴使领馆的统计，2017年以来，平均每月有近6000名巴基斯坦人申请签证前往中国，这个数字已经比2015年高了3.5倍。其中上海、北京、广州成为巴基斯坦人访问频率最高的中国城市。从2017年10月开始，中国国际航空公司将把从北京直飞伊斯兰堡的航班增加到每天一班，成为直飞巴基斯坦最为密集的外国航空公司之一。越来越多商人将中国商品带到巴基斯坦，大到家居用品，小到一只灯泡，都已进入巴基斯坦寻常百姓家。");
        newsList.add(news2);
        return newsList;
    }

}

